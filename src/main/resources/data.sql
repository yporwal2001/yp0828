INSERT INTO Tool (tool_id, tool_code, tool_type, brand) values (1, 'JAKR', 'Jackhammer', 'Ridgid');
INSERT INTO Tool (tool_id, tool_code, tool_type, brand) values (2, 'JAKD', 'Jackhammer', 'DeWalt');
INSERT INTO Tool (tool_id, tool_code, tool_type, brand) values (3, 'CHNS', 'Chainsaw', 'Stihl');
INSERT INTO Tool (tool_id, tool_code, tool_type, brand) values (4, 'LADW', 'Ladder', 'Werner');

INSERT INTO Tool_Charges (tool_charge_id, tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge) values (1, 'Jackhammer', '2.99','Y','N','N');
INSERT INTO Tool_Charges (tool_charge_id, tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge) values (2, 'Chainsaw', '1.49','Y','N','Y');
INSERT INTO Tool_Charges (tool_charge_id, tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge) values (3, 'Ladder', '1.99','Y','Y','N');

