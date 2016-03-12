CREATE TABLE occupied_dates
(
  id bigserial NOT NULL,
  id_reservation bigint,
  room integer,
  date date,
  CONSTRAINT id_occupied_dates PRIMARY KEY (id),
  CONSTRAINT link_reservation FOREIGN KEY (id_reservation)
      REFERENCES reservations (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);