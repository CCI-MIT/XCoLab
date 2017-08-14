-- COLAB-951
ALTER TABLE `xcolab_Contest` ADD UNIQUE `unique_index_contest_name_year`(`ContestShortName`, `ContestYear`);
