CREATE TABLE OPENJPA_SEQUENCE_TABLE (ID NUMBER NOT NULL, SEQUENCE_VALUE NUMBER, PRIMARY KEY (ID));
CREATE TABLE TEMPO_ITEM (id NUMBER NOT NULL, content_type VARCHAR2(255), lastmodified TIMESTAMP, payload BLOB, uri VARCHAR2(255), PRIMARY KEY (id));
