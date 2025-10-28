ALTER TABLE app_user RENAME COLUMN create_at TO created_at;


-- Define um valor padrão para registros novos
ALTER TABLE app_user ALTER COLUMN created_at SET DEFAULT NOW();

ALTER TABLE app_user ALTER COLUMN type SET DEFAULT 'CLIENT';

