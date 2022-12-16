DROP TABLE configs if exists;

CREATE TABLE configs
(
    id                 INTEGER PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    monitoring_enabled BOOLEAN      NOT NULL,
    cpu_enabled        BOOLEAN      NOT NULL,
    cpu_value          VARCHAR(255) NOT NULL
);

INSERT INTO configs (id, name, monitoring_enabled, cpu_enabled, cpu_value)
VALUES (2, 'datacenter-1', TRUE, FALSE, '700m');