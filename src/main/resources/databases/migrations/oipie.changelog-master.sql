-- liquibase formatted sql

-- changeset hector:1670845316256-1
CREATE TABLE public.users (user_id VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, nickname VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, CONSTRAINT "usersPK" PRIMARY KEY (user_id));

-- changeset hector:1670845316256-2
ALTER TABLE public.users ADD CONSTRAINT UC_USERSEMAIL_COL UNIQUE (email);

-- changeset hector:1670845316256-3
ALTER TABLE public.users ADD CONSTRAINT UC_USERSNICKNAME_COL UNIQUE (nickname);

-- changeset albertomr:1671030167938-3
CREATE TABLE public.recipes (recipe_id VARCHAR(255) NOT NULL, cover VARCHAR(255) NOT NULL, name VARCHAR(255) NOT NULL, preparation_time INTEGER NOT NULL, CONSTRAINT "recipesPK" PRIMARY KEY (recipe_id));


-- changeset albertomr:1671116543934-3
CREATE TABLE public.user_likes (recipe_id VARCHAR(255) NOT NULL, user_id VARCHAR(255) NOT NULL, CONSTRAINT "user_likesPK" PRIMARY KEY (recipe_id, user_id));

-- changeset albertomr:1671116543934-4
ALTER TABLE public.user_likes ADD CONSTRAINT "FK6aog39hkl1hs1amxef5i9g4fv" FOREIGN KEY (user_id) REFERENCES public.users (user_id);

-- changeset albertomr:1671116543934-5
ALTER TABLE public.user_likes ADD CONSTRAINT "FKjfbyexg39h7rdxaf794u8yqcu" FOREIGN KEY (recipe_id) REFERENCES public.recipes (recipe_id);


