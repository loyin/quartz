
set global read_only=0;
set global optimizer_switch='derived_merge=off'; 
create user 'root'@'%' identified by 'root';
create database quartz DEFAULT CHARSET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';   
grant all privileges on quartz.* to 'root'@'%' identified by 'root';
flush privileges;