\connect gamerproxela;

-- creacion de roles
CREATE ROLE admin;
CREATE ROLE cajero;
CREATE ROLE bodega;
CREATE ROLE inventario;

-- creacion de usuarios
CREATE USER administrador1 WITH PASSWORD 'admin123';
CREATE USER cajero1 WITH PASSWORD 'cajero123';
CREATE USER bodega1 WITH PASSWORD 'bodega123';
CREATE USER inventario1 WITH PASSWORD 'inventario123';

-- otorgando permisos para conectar a la base de datos
GRANT CONNECT ON DATABASE gamerproxela TO admin;
GRANT CONNECT ON DATABASE gamerproxela TO cajero;
GRANT CONNECT ON DATABASE gamerproxela TO bodega;
GRANT CONNECT ON DATABASE gamerproxela TO inventario;

-- añadiendo permisos a rol de admin
GRANT USAGE ON SCHEMA cp TO admin;
GRANT USAGE ON SCHEMA ce TO admin;
GRANT USAGE ON SCHEMA cv TO admin;
GRANT USAGE ON SCHEMA cc TO admin;
GRANT USAGE ON SCHEMA cd TO admin;
GRANT USAGE ON SCHEMA ci TO admin;
GRANT USAGE ON SCHEMA cb TO admin;

GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE cp.sucursal TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE cp.producto TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE cb.bodega TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE ci.inventario TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE ce.rol TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE ce.empleado TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE cv.venta TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE cv.detalle_venta TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE cc.cliente TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE cd.tarjeta TO admin;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE cd.historial_descuento TO admin;

-- añadiendo permisos a rol de cajero
GRANT USAGE ON SCHEMA cv TO cajero;
GRANT USAGE ON SCHEMA cc TO cajero;

GRANT INSERT,SELECT ON TABLE cv.venta TO cajero;
GRANT INSERT,SELECT ON TABLE cv.detalle_venta TO cajero;
GRANT INSERT,SELECT ON TABLE cc.cliente TO cajero;

-- añadiendo permisos a rol de bodega
GRANT USAGE ON SCHEMA cp TO bodega;
GRANT USAGE ON SCHEMA cb TO bodega;

GRANT INSERT,UPDATE,SELECT ON TABLE cp.producto TO bodega;
GRANT INSERT,UPDATE,SELECT ON TABLE cb.bodega TO bodega;

-- añadiendo permisos a rol inventario
GRANT USAGE ON SCHEMA cp TO inventario;
GRANT USAGE ON SCHEMA ci TO inventario;
GRANT USAGE ON SCHEMA cb TO inventario;

GRANT INSERT,UPDATE,SELECT ON TABLE cp.producto TO inventario;
GRANT INSERT,UPDATE,SELECT ON TABLE cb.bodega TO inventario;
GRANT INSERT,UPDATE,SELECT ON TABLE ci.inventario TO inventario;

-- otorgando role a usuarios
GRANT admin TO administrador1;
GRANT cajero TO cajero1;
GRANT bodega TO bodega1;
GRANT inventario TO inventario1;