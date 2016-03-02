CREATE TABLE vacation_dates
(
  id bigserial NOT NULL,
  id_vacation bigint,
  room integer,
  date date,
  CONSTRAINT id_vacation_dates PRIMARY KEY (id)
)