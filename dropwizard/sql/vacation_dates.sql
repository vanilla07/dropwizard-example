CREATE TABLE vacation_dates
(
  id bigserial NOT NULL,
  id_vacation bigint,
  room integer,
  date date,
  CONSTRAINT id_vacation_dates PRIMARY KEY (id),
  CONSTRAINT link_vacation FOREIGN KEY (id_vacation)
      REFERENCES vacation (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);