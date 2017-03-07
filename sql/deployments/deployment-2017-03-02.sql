ALTER TABLE xcolab_ContestPhaseRibbonType
  ADD COLUMN title VARCHAR(50) NOT NULL AFTER ribbon;
ALTER TABLE xcolab_ContestPhaseRibbonType
  ADD COLUMN showText TINYINT(4) NOT NULL AFTER hoverText;

UPDATE xcolab_ContestPhaseRibbonType SET title = '', showText = 1 WHERE id_ = 6;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Semi-finalist', showText = 1 WHERE id_ = 3;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Finalist', showText = 1 WHERE id_ = 1;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Finalist', showText = 1 WHERE id_ = 11;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Finalist', showText = 1 WHERE id_ = 12;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Finalist', showText = 1 WHERE id_ = 10;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Finalist', showText = 1 WHERE id_ = 14;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', , showText = 1 WHERE id_ = 2;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 4;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 5;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 7;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 8;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 9;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 13;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 15;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 16;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 17;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 18;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 19;
UPDATE xcolab_ContestPhaseRibbonType SET title = 'Winner', showText = 1 WHERE id_ = 20;

-- Solve only
-- INSERT INTO xcolab_ContestPhaseRibbonType (id_, ribbon, title, hoverText, showText, description, copyOnPromote, sortOrder) VALUES (21, 1, 'Solver', 'Solver', 0, null, 0, 1);