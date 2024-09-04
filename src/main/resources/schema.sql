CREATE SEQUENCE CONTRACT_SEQ START WITH 1 INCREMENT BY 1;
CREATE TABLE Tool (
  tool_id   INTEGER PRIMARY KEY,
  tool_code VARCHAR(64) NOT NULL,
  tool_type   VARCHAR(64) NOT NULL,
  brand VARCHAR(64) NOT NULL);

  CREATE TABLE Tool_Charges (
    tool_charge_id  INTEGER PRIMARY KEY,
    tool_type VARCHAR(64) NOT NULL,
    daily_charge  DOUBLE NOT NULL,
    weekday_charge BIT NOT NULL,
    weekend_charge BIT NOT NULL,
    holiday_charge BIT NOT NULL);

   CREATE TABLE Contract (
      contract_id  INTEGER PRIMARY KEY,
      tool_code VARCHAR(64) NOT NULL,
      tool_type VARCHAR(64) NOT NULL,
      brand VARCHAR(64) NOT NULL,
      rental_days INTEGER NOT NULL,
      checkout_date DATE NOT NULL,
      due_date DATE NOT NULL,
      daily_rental_charge DOUBLE NOT NULL,
      charge_days INTEGER NOT NULL,
      pre_discount_charge DOUBLE NOT NULL,
      discount_percent INTEGER NULL,
      discount_amount DOUBLE NULL,
      final_charge   DOUBLE NOT NULL);