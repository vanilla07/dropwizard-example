CREATE TABLE vacation
(
  id bigserial NOT NULL,
  room integer,
  name character varying(255),
  notes text,
  date_start date,
  date_end date,
  CONSTRAINT id_vacation PRIMARY KEY (id)
);