CREATE DATABASE gamerproxela;

\connect gamerproxela;

-- control productos
CREATE SCHEMA cp;
-- control empleados
CREATE SCHEMA ce;
-- control ventas
CREATE SCHEMA cv;
-- control clientes
CREATE SCHEMA cc;
-- control descuentos
CREATE SCHEMA cd;
-- control inventario
CREATE SCHEMA ci;
-- control bodega
CREATE SCHEMA cb;

-- crear tablas dentro del esquema de control de productos
CREATE TABLE cp.sucursal (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(100) NOT NULL
);

CREATE TABLE cp.producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT,
    precio NUMERIC(10, 2) NOT NULL
);

-- crear tablas dentro del esquema de control de bodega
CREATE TABLE cb.bodega (
    id SERIAL PRIMARY KEY,
    sucursal_id INT REFERENCES cp.sucursal(id),
    producto_id INT REFERENCES cp.producto(id),
    cantidad INT NOT NULL
);

-- crear tablas dentro del esquema de control de inventario
CREATE TABLE ci.inventario (
    id SERIAL PRIMARY KEY,
    sucursal_id INT REFERENCES cp.sucursal(id),
    producto_id INT REFERENCES cp.producto(id),
    cantidad INT NOT NULL,
    pasillo VARCHAR(5) NOT NULL
);

-- crear tablas dentro del esquema de control de empleados
CREATE TABLE ce.rol (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE ce.empleado (
    cui VARCHAR(13) UNIQUE PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    rol_id INT REFERENCES ce.rol(id),
    sucursal_id INT REFERENCES cp.sucursal(id),
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    caja INT
);

CREATE TABLE cd.tarjeta (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,
    puntos_por_cada_200 NUMERIC(10, 2) NOT NULL
);

-- crear tablas dentro del esquema de control de clientes
CREATE TABLE cc.cliente (
    nit VARCHAR(20) UNIQUE PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(60) NOT NULL,
    direccion VARCHAR(60) NOT NULL,
    id_tarjeta INT REFERENCES cd.tarjeta(id)
);


CREATE TABLE cd.historial_descuento (
    cliente_id VARCHAR(20) REFERENCES cc.cliente(nit),
    tarjeta_id INT REFERENCES cd.tarjeta(id),
    puntos_utilizados INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cliente_id, tarjeta_id)
);

-- crear tablas dentro del esquema de control de ventas
CREATE TABLE cv.venta (
    id SERIAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cliente_id VARCHAR(20) REFERENCES cc.cliente(nit),
    cajero_id VARCHAR(13) REFERENCES ce.empleado(cui),
    total_sin_descuentos NUMERIC(10, 2) NOT NULL,
    total_con_descuentos NUMERIC(10, 2) NOT NULL
);

CREATE TABLE cv.detalle_venta (
    venta_id INT REFERENCES cv.venta(id),
    producto_id INT REFERENCES cp.producto(id),
    cantidad INT NOT NULL,
    precio_unitario NUMERIC(10, 2) NOT NULL,
    PRIMARY KEY (venta_id, producto_id)
);

