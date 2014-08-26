CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `home_team_id` int(4) DEFAULT NULL,
  `guest_team_id` int(4) DEFAULT NULL,
  `win` float(4,2) DEFAULT NULL,
  `draw` float(4,2) DEFAULT NULL,
  `lose` float(4,2) DEFAULT NULL,
  `let_the_ball` int(1) DEFAULT NULL,
  `home_score` int(1) DEFAULT NULL,
  `guest_score` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
)