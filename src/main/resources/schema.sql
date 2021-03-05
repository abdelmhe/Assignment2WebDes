DROP TABLE IF EXISTS TEAMS;

CREATE TABLE `TEAMS` (
                         team_id INT AUTO_INCREMENT PRIMARY KEY,
                         team_name VARCHAR(250) NOT NULL,
                         team_flag VARCHAR(500) NOT NULL,
                         continent VARCHAR(250) NOT NULL,
                         played INT(50) DEFAULT NULL,
                         won INT(50) DEFAULT NULL,
                         drawn INT(50) DEFAULT NULL,
                         lost INT(50) DEFAULT NULL
);
