-- -----------------------------------------------------
-- Table servico
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS servico (
  id_servico INT NOT NULL,
  nome_servico VARCHAR(100) ,
  descricao VARCHAR(200) ,
  preco DOUBLE ,
  duracao INT ,
  PRIMARY KEY (id_servico));


-- -----------------------------------------------------
-- Table manicure
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS manicure (
  id_manicure INT NOT NULL,
  nome_manicure VARCHAR(100) ,
  PRIMARY KEY (id_manicure));

-- -----------------------------------------------------
-- Table cliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cliente (
  id_cliente INT NOT NULL,
  nome_cliente VARCHAR(100) ,
  id_facebook VARCHAR(100) ,
  PRIMARY KEY (id_cliente));


-- -----------------------------------------------------
-- Table atendimento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS atendimento (
  id_atendimento INT NOT NULL,
  id_cliente INT NOT NULL,
  id_manicure INT NOT NULL,
  data_inicio DATE ,
  data_fim DATE ,
  status_atendimento CHAR(1) ,
  PRIMARY KEY (id_atendimento),
  CONSTRAINT fk_atendimento_cliente1
    FOREIGN KEY (id_cliente)
    REFERENCES cliente (id_cliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_atendimento_manicure1
    FOREIGN KEY (id_manicure)
    REFERENCES manicure (id_manicure)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table agenda_manicure
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS agenda_manicure (
  id_agenda_manicure INT NOT NULL,
  data_inicio DATE ,
  data_fim DATE ,
  status_agenda CHAR(1) ,
  id_manicure INT NOT NULL,
  PRIMARY KEY (id_agenda_manicure),
  CONSTRAINT fk_agenda_manicure_manicure
    FOREIGN KEY (id_manicure)
    REFERENCES manicure (id_manicure)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table servico_atendimento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS servico_atendimento (
  id_servico INT NOT NULL,
  id_atendimento INT NOT NULL,
  PRIMARY KEY (id_servico, id_atendimento),
  CONSTRAINT fk_servico_atendimento_servico
    FOREIGN KEY (id_servico)
    REFERENCES servico (id_servico)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_servico_atendimento_atendimento
    FOREIGN KEY (id_atendimento)
    REFERENCES atendimento (id_atendimento)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

