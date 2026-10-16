CREATE DATABASE IF NOT EXISTS Proyectodas;
USE Proyectodas;

CREATE TABLE ROLES (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL UNIQUE,
    descripcion VARCHAR(200),
    activo BIT NOT NULL DEFAULT 1,

    CONSTRAINT CK_ROLES_NOMBRE
        CHECK (nombre IN ('admin', 'server', 'cocinero', 'cajero'))
);

CREATE TABLE PERSONAL (
    id_personal INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    activo BIT NOT NULL DEFAULT 1,
    fecha_ingreso DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE USUARIOS (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    id_personal INT NOT NULL,
    id_rol INT NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(80) NULL,
    pin_hash VARCHAR(80) NULL,
    activo BIT NOT NULL DEFAULT 1,
    intentos_fallidos INT DEFAULT 0,
    inicio_bloqueo DATETIME NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_USUARIO_PERSONAL
        FOREIGN KEY (id_personal)
        REFERENCES PERSONAL(id_personal),

    CONSTRAINT FK_USUARIO_ROL
        FOREIGN KEY (id_rol)
        REFERENCES ROLES(id_rol)
);

CREATE TABLE MESAS (
    id_mesa INT AUTO_INCREMENT PRIMARY KEY,
    numero_mesa INT NOT NULL UNIQUE,
    capacidad INT NOT NULL,
    ubicacion VARCHAR(100),
    activa BIT NOT NULL DEFAULT 1,
    estado VARCHAR(30) NOT NULL DEFAULT 'LIBRE',

    CONSTRAINT CK_MESA_CAPACIDAD
        CHECK (capacidad > 0),

    CONSTRAINT CK_MESA_ESTADO
        CHECK (estado IN ('LIBRE', 'OCUPADA', 'TRANSICION'))
);

CREATE TABLE CLIENTES (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(150) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    documento VARCHAR(30),
    telefono VARCHAR(20),
    correo VARCHAR(150),
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- NUEVA: Lista de Espera Digital en Puerta (ECU-03 / RF-29)
CREATE TABLE LISTA_ESPERA (
    id_espera INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(150) NOT NULL,
    cantidad_personas INT NOT NULL,
    tiempo_estimado_min INT NOT NULL,
    fecha_llegada DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(30) NOT NULL DEFAULT 'EN_ESPERA',

    CONSTRAINT CK_ESPERA_ESTADO
        CHECK (estado IN ('EN_ESPERA', 'ASIGNADO', 'CANCELADO'))
);

CREATE TABLE CATEGORIAS (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    activo BIT NOT NULL DEFAULT 1
);

CREATE TABLE PRODUCTOS (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    id_categoria INT NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(500),
    precio DECIMAL(10,2) NOT NULL,
    disponible BIT NOT NULL DEFAULT 1,
    activo BIT NOT NULL DEFAULT 1,
    tiempo_alerta_amarilla INT NOT NULL DEFAULT 10, -- RNF-12.2
    tiempo_alerta_roja INT NOT NULL DEFAULT 15,       -- RNF-12.2

    CONSTRAINT FK_PRODUCTO_CATEGORIA
        FOREIGN KEY (id_categoria)
        REFERENCES CATEGORIAS(id_categoria),

    CONSTRAINT CK_PRODUCTO_PRECIO
        CHECK (precio >= 0),

    CONSTRAINT CK_PRODUCTO_ALERTAS
        CHECK (tiempo_alerta_amarilla < tiempo_alerta_roja)
);

-- NUEVA: Historial de Precios para Auditoría
CREATE TABLE HISTORIAL_PRECIOS (
    id_historial_precio INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    precio_anterior DECIMAL(10,2) NOT NULL,
    precio_nuevo DECIMAL(10,2) NOT NULL,
    id_usuario_cambia INT NOT NULL,
    fecha_cambio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    motivo VARCHAR(255),

    CONSTRAINT FK_HIST_PRECIO_PRODUCTO
        FOREIGN KEY (id_producto)
        REFERENCES PRODUCTOS(id_producto),

    CONSTRAINT FK_HIST_PRECIO_USUARIO
        FOREIGN KEY (id_usuario_cambia)
        REFERENCES USUARIOS(id_usuario)
);

CREATE TABLE INSUMOS (
    id_insumo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL UNIQUE,
    unidad_medida VARCHAR(30) NOT NULL,
    stock_actual DECIMAL(12,2) NOT NULL DEFAULT 0,
    stock_minimo DECIMAL(12,2) NOT NULL DEFAULT 0,
    activo BIT NOT NULL DEFAULT 1,

    CONSTRAINT CK_STOCK_ACTUAL
        CHECK (stock_actual >= 0),

    CONSTRAINT CK_STOCK_MINIMO
        CHECK (stock_minimo >= 0)
);

CREATE TABLE MOVIMIENTOS_INVENTARIO (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_insumo INT NOT NULL,
    id_usuario INT NULL,
    tipo_movimiento VARCHAR(30) NOT NULL,
    cantidad DECIMAL(12,2) NOT NULL,
    stock_anterior DECIMAL(12,2) NULL,
    stock_nuevo DECIMAL(12,2) NULL,
    motivo VARCHAR(255),
    fecha_movimiento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_MOV_INV_INSUMO
        FOREIGN KEY (id_insumo)
        REFERENCES INSUMOS(id_insumo),

    CONSTRAINT FK_MOV_INV_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIOS(id_usuario),

    CONSTRAINT CK_MOV_INV_TIPO
        CHECK (
            tipo_movimiento IN
            ('INGRESO', 'SALIDA', 'MERMA', 'AJUSTE', 'CONSUMO_RECETA', 'SUSTITUCION')
        ),

    CONSTRAINT CK_MOV_INV_CANTIDAD
        CHECK (cantidad > 0)
);

CREATE TABLE INSUMOS_SUSTITUTOS (
    id_insumo INT NOT NULL,
    id_insumo_sustituto INT NOT NULL,
    observacion VARCHAR(255),

    PRIMARY KEY (id_insumo, id_insumo_sustituto),

    CONSTRAINT FK_SUSTITUTO_ORIGINAL
        FOREIGN KEY (id_insumo)
        REFERENCES INSUMOS(id_insumo),

    CONSTRAINT FK_SUSTITUTO_REEMPLAZO
        FOREIGN KEY (id_insumo_sustituto)
        REFERENCES INSUMOS(id_insumo),

    CONSTRAINT CK_NO_MISMO_INSUMO
        CHECK (id_insumo <> id_insumo_sustituto)
);

CREATE TABLE RECETAS (
    id_receta INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL UNIQUE,
    instrucciones TEXT,

    CONSTRAINT FK_RECETA_PRODUCTO
        FOREIGN KEY (id_producto)
        REFERENCES PRODUCTOS(id_producto)
);

CREATE TABLE RECETA_DETALLE (
    id_receta_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_receta INT NOT NULL,
    id_insumo INT NOT NULL,
    cantidad DECIMAL(12,2) NOT NULL,

    CONSTRAINT FK_RECETA_DETALLE_RECETA
        FOREIGN KEY (id_receta)
        REFERENCES RECETAS(id_receta),

    CONSTRAINT FK_RECETA_DETALLE_INSUMO
        FOREIGN KEY (id_insumo)
        REFERENCES INSUMOS(id_insumo),

    CONSTRAINT CK_RECETA_CANTIDAD
        CHECK (cantidad > 0),

    CONSTRAINT UQ_RECETA_INSUMO
        UNIQUE (id_receta, id_insumo)
);

CREATE TABLE CUENTAS (
    id_cuenta INT AUTO_INCREMENT PRIMARY KEY,
    id_mesa INT NOT NULL,
    id_cliente INT NULL,
    id_usuario INT NOT NULL,
    fecha_apertura DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre DATETIME NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'ABIERTA',
    total DECIMAL(10,2) NOT NULL DEFAULT 0,

    CONSTRAINT FK_CUENTA_MESA
        FOREIGN KEY (id_mesa)
        REFERENCES MESAS(id_mesa),

    CONSTRAINT FK_CUENTA_CLIENTE
        FOREIGN KEY (id_cliente)
        REFERENCES CLIENTES(id_cliente),

    CONSTRAINT FK_CUENTA_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIOS(id_usuario),

    CONSTRAINT CK_CUENTA_ESTADO
        CHECK (estado IN ('ABIERTA', 'CERRADA', 'ANULADA', 'FUSIONADA')),

    CONSTRAINT CK_CUENTA_TOTAL
        CHECK (total >= 0)
);

CREATE TABLE PEDIDOS (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_pedido DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
    observaciones VARCHAR(500),

    CONSTRAINT FK_PEDIDO_CUENTA
        FOREIGN KEY (id_cuenta)
        REFERENCES CUENTAS(id_cuenta),

    CONSTRAINT FK_PEDIDO_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIOS(id_usuario),

    CONSTRAINT CK_PEDIDO_ESTADO
        CHECK (
            estado IN
            ('PENDIENTE', 'ENVIADO', 'EN_PREPARACION', 'PARCIALMENTE_LISTO', 'LISTO', 'ENTREGADO', 'CANCELADO')
        )
);

CREATE TABLE DETALLE_PEDIDO (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    comentarios VARCHAR(500),
    estado_preparacion VARCHAR(30) NOT NULL DEFAULT 'RECIBIDO',
    fecha_recibido DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_inicio_preparacion DATETIME NULL,
    fecha_listo DATETIME NULL,
    fecha_retirado DATETIME NULL,
    fecha_cancelado DATETIME NULL,

    CONSTRAINT FK_DETALLE_PEDIDO
        FOREIGN KEY (id_pedido)
        REFERENCES PEDIDOS(id_pedido),

    CONSTRAINT FK_DETALLE_PRODUCTO
        FOREIGN KEY (id_producto)
        REFERENCES PRODUCTOS(id_producto),

    CONSTRAINT CK_DETALLE_CANTIDAD
        CHECK (cantidad > 0),

    CONSTRAINT CK_DETALLE_PRECIO
        CHECK (precio_unitario >= 0),

    CONSTRAINT CK_DETALLE_SUBTOTAL
        CHECK (subtotal >= 0),

    CONSTRAINT CK_DETALLE_ESTADO
        CHECK (estado_preparacion IN ('RECIBIDO', 'EN_PREPARACION', 'LISTO', 'RETIRADO', 'CANCELADO'))
);

CREATE TABLE HISTORIAL_COMANDA (
    id_historial BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_detalle INT NOT NULL,
    estado_anterior VARCHAR(30) NULL,
    estado_nuevo VARCHAR(30) NOT NULL,
    id_usuario INT NULL,
    fecha_cambio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacion VARCHAR(500),
    
    CONSTRAINT FK_HISTORIAL_DETALLE
        FOREIGN KEY (id_detalle)
        REFERENCES DETALLE_PEDIDO(id_detalle),
        
    CONSTRAINT FK_HISTORIAL_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIOS(id_usuario)
);

CREATE TABLE MERMAS (
    id_merma INT AUTO_INCREMENT PRIMARY KEY,
    id_insumo INT NOT NULL,
    cantidad DECIMAL(12,2) NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    id_administrador_autoriza INT NULL, -- RNF-19.3
    
    CONSTRAINT FK_MERMA_INSUMO
        FOREIGN KEY (id_insumo)
        REFERENCES INSUMOS(id_insumo),
        
    CONSTRAINT FK_MERMA_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIOS(id_usuario),

    CONSTRAINT FK_MERMA_ADMIN_AUTORIZA
        FOREIGN KEY (id_administrador_autoriza)
        REFERENCES USUARIOS(id_usuario),
        
    CONSTRAINT CK_MERMA_CANTIDAD
        CHECK (cantidad > 0)
);

CREATE TABLE INCIDENTES_COCINA (
    id_incidente INT AUTO_INCREMENT PRIMARY KEY,
    id_detalle INT NULL,
    descripcion VARCHAR(500) NOT NULL,
    fecha_incidente DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
    
    CONSTRAINT FK_INCIDENTE_DETALLE_PEDIDO
        FOREIGN KEY (id_detalle)
        REFERENCES DETALLE_PEDIDO(id_detalle),
        
    CONSTRAINT FK_INCIDENTE_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIOS(id_usuario),
        
    CONSTRAINT CK_INCIDENTE_ESTADO
        CHECK (estado IN ('PENDIENTE', 'RESUELTO', 'CANCELADO'))
);

CREATE TABLE PROMOCIONES (
    id_promocion INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(500),
    tipo_descuento VARCHAR(30) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME NOT NULL,
    activa BIT NOT NULL DEFAULT 1,
    
    CONSTRAINT CK_PROMOCION_TIPO
        CHECK (tipo_descuento IN ('PORCENTAJE', 'MONTO')),
        
    CONSTRAINT CK_PROMOCION_VALOR
        CHECK (
            (tipo_descuento = 'PORCENTAJE' AND valor BETWEEN 0 AND 100)
            OR (tipo_descuento = 'MONTO' AND valor >= 0)
        ),
        
    CONSTRAINT CK_PROMOCION_FECHAS
        CHECK (fecha_fin >= fecha_inicio)
);

CREATE TABLE CUENTA_PROMOCION (
    id_cuenta INT NOT NULL,
    id_promocion INT NOT NULL,
    descuento DECIMAL(10,2) NOT NULL,
    id_usuario_autorizador INT NULL,
    fecha_aplicacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_cuenta, id_promocion),
    
    CONSTRAINT FK_CP_CUENTA
        FOREIGN KEY (id_cuenta)
        REFERENCES CUENTAS(id_cuenta),
        
    CONSTRAINT FK_CP_PROMOCION
        FOREIGN KEY (id_promocion)
        REFERENCES PROMOCIONES(id_promocion),
        
    CONSTRAINT FK_CP_AUTORIZADOR
        FOREIGN KEY (id_usuario_autorizador)
        REFERENCES USUARIOS(id_usuario),
        
    CONSTRAINT CK_CP_DESCUENTO
        CHECK (descuento >= 0)
);

CREATE TABLE TURNOS_CAJA (
    id_turno INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_apertura DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre DATETIME NULL,
    monto_inicial DECIMAL(10,2) NOT NULL,
    monto_final DECIMAL(10,2) NULL,
    diferencia DECIMAL(10,2) NULL,
    observacion_cierre VARCHAR(500),
    estado VARCHAR(30) NOT NULL DEFAULT 'ABIERTO',
    
    CONSTRAINT FK_TURNO_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIOS(id_usuario),
        
    CONSTRAINT CK_TURNO_MONTO
        CHECK (monto_inicial >= 0),
        
    CONSTRAINT CK_TURNO_ESTADO
        CHECK (estado IN ('ABIERTO', 'CERRADO', 'CANCELADO'))
);

CREATE TABLE MOVIMIENTOS_CAJA (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_turno INT NOT NULL,
    tipo_movimiento VARCHAR(30) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    descripcion VARCHAR(255),
    fecha_movimiento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_CAJA_TURNO
        FOREIGN KEY (id_turno)
        REFERENCES TURNOS_CAJA(id_turno),
    CONSTRAINT CK_CAJA_TIPO
        CHECK (tipo_movimiento IN ('INGRESO', 'EGRESO')),
    CONSTRAINT CK_CAJA_MONTO
        CHECK (monto > 0)
);

-- Actualizado con YAPE y PLIN para pagos digitales (RF-36)
CREATE TABLE METODOS_PAGO (
    id_metodo_pago INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    activo BIT NOT NULL DEFAULT 1,
    
    CONSTRAINT CK_METODO_PAGO
        CHECK (nombre IN ('EFECTIVO', 'TARJETA', 'YAPE', 'PLIN', 'TRANSFERENCIA'))
);

CREATE TABLE PAGOS (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT NOT NULL,
    id_turno INT NOT NULL,
    id_metodo_pago INT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    monto_recibido DECIMAL(10,2) NULL,
    vuelto DECIMAL(10,2) NULL,
    fecha_pago DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    referencia VARCHAR(150),
    estado VARCHAR(30) NOT NULL DEFAULT 'PAGADO',
    
    CONSTRAINT FK_PAGO_CUENTA
        FOREIGN KEY (id_cuenta)
        REFERENCES CUENTAS(id_cuenta),
        
    CONSTRAINT FK_PAGO_TURNO
        FOREIGN KEY (id_turno)
        REFERENCES TURNOS_CAJA(id_turno),
        
    CONSTRAINT FK_PAGO_METODO
        FOREIGN KEY (id_metodo_pago)
        REFERENCES METODOS_PAGO(id_metodo_pago),
        
    CONSTRAINT CK_PAGO_MONTO
        CHECK (monto > 0),
        
    CONSTRAINT CK_PAGO_RECIBIDO
        CHECK (monto_recibido IS NULL OR monto_recibido >= monto),
        
    CONSTRAINT CK_PAGO_VUELTO
        CHECK (vuelto IS NULL OR vuelto >= 0),
        
    CONSTRAINT CK_PAGO_ESTADO
        CHECK (estado IN ('PAGADO', 'ANULADO', 'PENDIENTE'))
);

-- NUEVA: Gestión y distribución de Propinas (RF-34)
CREATE TABLE PROPINAS_TURNO (
    id_propina INT AUTO_INCREMENT PRIMARY KEY,
    id_turno INT NOT NULL,
    id_usuario_mozo INT NOT NULL,
    monto_propina DECIMAL(12,4) NOT NULL,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_PROPINA_TURNO
        FOREIGN KEY (id_turno)
        REFERENCES TURNOS_CAJA(id_turno),

    CONSTRAINT FK_PROPINA_MOZO
        FOREIGN KEY (id_usuario_mozo)
        REFERENCES USUARIOS(id_usuario),

    CONSTRAINT CK_PROPINA_MONTO
        CHECK (monto_propina >= 0)
);

CREATE TABLE COMPROBANTES (
    id_comprobante INT AUTO_INCREMENT PRIMARY KEY,
    id_cuenta INT NOT NULL,
    tipo_comprobante VARCHAR(30) NOT NULL,
    serie VARCHAR(10) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    fecha_emision DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    subtotal DECIMAL(10,2) NOT NULL,
    impuesto DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    estado_sunat VARCHAR(30),
    codigo_hash VARCHAR(80) NULL,
    enlace_encuesta VARCHAR(500),
    xml_sunat_url VARCHAR(255) NULL,
    
    CONSTRAINT FK_COMPROBANTE_CUENTA
        FOREIGN KEY (id_cuenta)
        REFERENCES CUENTAS(id_cuenta),
        
    CONSTRAINT UQ_COMPROBANTE
        UNIQUE (serie, numero),
        
    CONSTRAINT CK_COMPROBANTE_IMPORTES
        CHECK (subtotal >= 0 AND impuesto >= 0 AND total >= 0)
);

CREATE TABLE NOTAS_CREDITO (
    id_nota_credito INT AUTO_INCREMENT PRIMARY KEY,
    id_comprobante_origen INT NOT NULL,
    id_administrador INT NOT NULL,
    serie_correlativo VARCHAR(20) NOT NULL UNIQUE,
    motivo_devolucion TEXT NOT NULL,
    monto_devuelto DECIMAL(10,2) NOT NULL,
    estado_sunat VARCHAR(30) DEFAULT 'ACEPTADO',
    fecha_emision DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_NC_COMPROBANTE
        FOREIGN KEY (id_comprobante_origen)
        REFERENCES COMPROBANTES(id_comprobante),

    CONSTRAINT FK_NC_ADMIN
        FOREIGN KEY (id_administrador)
        REFERENCES USUARIOS(id_usuario),

    CONSTRAINT CK_NC_MONTO
        CHECK (monto_devuelto > 0)
);

CREATE TABLE AUTORIZACIONES_PIN (
    id_autorizacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario_solicitante INT NOT NULL,
    id_usuario_autorizador INT NULL,
    operacion VARCHAR(150) NOT NULL,
    fecha_autorizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    resultado VARCHAR(30) NOT NULL,
    observacion VARCHAR(500),
    
    CONSTRAINT FK_PIN_SOLICITANTE
        FOREIGN KEY (id_usuario_solicitante)
        REFERENCES USUARIOS(id_usuario),
        
    CONSTRAINT FK_PIN_AUTORIZADOR
        FOREIGN KEY (id_usuario_autorizador)
        REFERENCES USUARIOS(id_usuario),
        
    CONSTRAINT CK_PIN_RESULTADO
        CHECK (resultado IN ('EXITOSO', 'DENEGADO'))
);

CREATE TABLE AUDITORIA (
    id_auditoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NULL,
    accion VARCHAR(100) NOT NULL,
    tabla_afectada VARCHAR(100),
    registro_id INT NULL,
    descripcion VARCHAR(1000),
    fecha DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    
    CONSTRAINT FK_AUDITORIA_USUARIO
        FOREIGN KEY (id_usuario)
        REFERENCES USUARIOS(id_usuario)
);



