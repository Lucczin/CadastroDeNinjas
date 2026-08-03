-- V2: Migrations para adicionar a coluna RANK na tabela cadastros

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255);