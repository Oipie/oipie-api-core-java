-- liquibase formatted sql

-- changeset hector:1670845316256-1
CREATE TABLE public.users (user_id VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, nickname VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, CONSTRAINT "usersPK" PRIMARY KEY (user_id));

-- changeset hector:1670845316256-2
ALTER TABLE public.users ADD CONSTRAINT UC_USERSEMAIL_COL UNIQUE (email);

-- changeset hector:1670845316256-3
ALTER TABLE public.users ADD CONSTRAINT UC_USERSNICKNAME_COL UNIQUE (nickname);


-- changeset albertomr:1671030167938-3
CREATE TABLE public.recipes (recipe_id VARCHAR(255) NOT NULL, cover VARCHAR(255) NOT NULL, name VARCHAR(255) NOT NULL, preparation_time INTEGER NOT NULL, CONSTRAINT "recipesPK" PRIMARY KEY (recipe_id));


