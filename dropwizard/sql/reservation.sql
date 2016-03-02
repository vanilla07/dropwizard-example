CREATE TABLE reservations
(
  id bigserial NOT NULL,
  room integer,
  name character varying(255),
  email character varying(255),
  guests_number integer,
  date_in date,
  date_out date,
  date_reservation date,
  status integer,
  notes text,
  telephone character varying(50),
  channel integer,
  CONSTRAINT id_reservation PRIMARY KEY (id)
)