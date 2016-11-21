CREATE TABLE IF NOT EXISTS UserConnection (userId varchar(255) not null,
    providerId varchar(255) not null,
    providerUserId varchar(255),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(255) not null,					
    secret varchar(255),
    refreshToken varchar(255),
    expireTime bigint,
    primary key (userId, providerId, providerUserId));

insert into UserConnection(userId,providerId,providerUserId,rank,displayName,profileUrl,imageUrl,accessToken,secret,refreshToken,expireTime)
   values('equipodoctoralevy@gmail.com', 'google', '110694601188641714803', 1, 'Marcelo Mazzola', 'https://plus.google.com/110694601188641714803',
    'https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg?sz=50', 'ya29.SgGRjpHBMCNdHao_ZYBhAbFmhuf6F6X8FXt9PV8KzwfSNeJCXhY7Pj2ChPO1QVkdG2-G1b8FpV15eA',
    null, '4/-eRDaCmX360Tkl5G_TpOeij10sd3LPRvtEyVa3NrTkQ', 1428098622863);

insert into usuario(id,usuario_google,email,nombre,enabled,username,password) values('1','1','balbifm@gmail.com','balbifm@gmail.com','1','balbifm@gmail.com','.');
insert into usuario(id,usuario_google,email,nombre,enabled,username,password) values('2','0','balbifm@gmail.com','admin','1','admin','a40546cc4fd6a12572828bb803380888ad1bfdab');
insert into usuario(id,usuario_google,email,nombre,enabled,username,password) values('3','1','fede@ni54.com','fede@ni54.com','1','fede@ni54.com','.');
insert into usuario(id,usuario_google,email,nombre,enabled,username,password) values('4','0','balbifm@gmail.com','user','1','user','b6b1f4781776979c0775c71ebdd8bdc084aac5fe');

insert into grupo(id,enabled,nombre) values('1','1','administrador');

insert into usuario_grupos (usuario, grupos) values ('1', '1');
insert into usuario_grupos (usuario, grupos) values ('2', '1');
insert into usuario_grupos (usuario, grupos) values ('3', '1');
insert into usuario_grupos (usuario, grupos) values ('4', '1');

insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ADMINISTRAR_GRUPOS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ADMINISTRAR_USUARIOS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ADMINISTRAR_FICHAS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'CONFIGURAR_FICHAS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'EDITAR_CONSULTAS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'VER_CONSULTAS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ELIMINAR_CONSULTAS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'BUSCAR_PACIENTES');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'BUSCAR_CONSULTAS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'CREAR_DATOS_FILIATORIOS');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'CREAR_CONSULTA');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ADMINISTRAR_EVOLUCION');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ADMINISTAR_INTERNACION');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ADMINISTRAR_PLAN');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ADMINISTRAR_CIRUGIA');
insert into grupo_permisos_usuarios (grupo, permisos_usuarios) values ('1', 'ADMINISTRAR_ALTA');
    
