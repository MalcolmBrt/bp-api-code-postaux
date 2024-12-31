CREATE SCHEMA IF NOT EXISTS sibp_adm;
GRANT USAGE ON SCHEMA sibp_adm to sibp_adm;
DROP SCHEMA IF EXISTS public;
ALTER DATABASE sibp SET search_path TO sibp_adm;
