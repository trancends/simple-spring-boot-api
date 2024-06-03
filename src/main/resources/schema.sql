CREATE TABLE IF NOT EXIST Run  (
  id INT NOT NULL,
  title VARCHAR(255) NOT NULL,
  started_on timpestamp NOT NULL,
  completed_on timestamp NOT NULL,
  miles INT NOT NULL,
  location VARCHAR(10) NOT NULL,
  PRIMARY KEY(id)
)

