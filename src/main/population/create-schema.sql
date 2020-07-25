
    create table `accounting` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation` datetime(6),
        `status` bit not null,
        `title` varchar(255),
        `bookkeeper_id` integer,
        `round_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity` (
       `id` integer not null,
        `version` integer not null,
        `budget_amount` double precision,
        `budget_currency` varchar(255),
        `end` datetime(6),
        `start` datetime(6),
        `title` varchar(255),
        `round_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `justification` varchar(255),
        `offer_amount` double precision,
        `offer_currency` varchar(255),
        `statement` varchar(255),
        `status` varchar(255),
        `ticker` varchar(255),
        `investor_id` integer not null,
        `round_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authorization` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `accepted` bit not null,
        `body` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `url` varchar(255),
        `card_id` integer,
        `patron_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsibility` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `botia_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `expiring_date` datetime(6),
        `moment` datetime(6),
        `text_body` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `card` (
       `id` integer not null,
        `version` integer not null,
        `brand` varchar(255),
        `cvv` varchar(255),
        `holder` varchar(255),
        `number` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `average_goal` varchar(255),
        `average_reward_amount` double precision,
        `average_reward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `expert_goal` varchar(255),
        `expert_reward_amount` double precision,
        `expert_reward_currency` varchar(255),
        `rookie_goal` varchar(255),
        `rookie_reward_amount` double precision,
        `rookie_reward_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customization` (
       `id` integer not null,
        `version` integer not null,
        `sectors` varchar(255),
        `spam` varchar(255),
        `threshold` double precision,
        primary key (`id`)
    ) engine=InnoDB;

    create table `entrepreneur` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualification` varchar(255),
        `sector` varchar(255),
        `skills` varchar(255),
        `startup` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `forum` (
       `id` integer not null,
        `version` integer not null,
        `creator_id` integer not null,
        `round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquiry` (
       `id` integer not null,
        `version` integer not null,
        `contact` varchar(255),
        `creation` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `money_amount` double precision,
        `money_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `profile` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `forum_id` integer not null,
        `user_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation` datetime(6),
        `deadline` datetime(6),
        `header` varchar(255),
        `related` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `contact` varchar(255),
        `creation` datetime(6),
        `deadline` datetime(6),
        `description` varchar(255),
        `money_amount` double precision,
        `money_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `participant` (
       `id` integer not null,
        `version` integer not null,
        `forum_id` integer not null,
        `user_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `patron` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `organisation` varchar(255),
        `card_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `round` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `description` varchar(255),
        `information` varchar(255),
        `kind` varchar(255),
        `money_amount` double precision,
        `money_currency` varchar(255),
        `status` bit not null,
        `ticker` varchar(255),
        `title` varchar(255),
        `entrepreneur_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology` (
       `id` integer not null,
        `version` integer not null,
        `contact` varchar(255),
        `description` varchar(255),
        `inventor` varchar(255),
        `sector` varchar(255),
        `source` varchar(255),
        `stars` double precision,
        `title` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool` (
       `id` integer not null,
        `version` integer not null,
        `contact` varchar(255),
        `description` varchar(255),
        `inventor` varchar(255),
        `sector` varchar(255),
        `source` varchar(255),
        `stars` double precision,
        `title` varchar(255),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXfmpdf5k3txn94kwvoi2m1jge4 on `accounting` (`status`);
create index IDX7w050mi7toy4boa363tvcxr2w on `activity` (`end`);
create index IDX2q2747fhp099wkn3j2yt05fhs on `application` (`status`);
create index IDX6fmsp547p4ql4cgit2hk0uxjs on `application` (`creation`);
create index IDXj1shjic6mip5nyik4ywhvxiid on `application` (`ticker`);

    alter table `application` 
       add constraint UK_ao7wxw7e7mkj6g5q49yq2fw8d unique (`ticker`);
create index IDXorxhsdg7d67bolj0n57rc4o63 on `botia_bulletin` (`expiring_date`);
create index IDXnr284tes3x8hnd3h716tmb3fr on `challenge` (`deadline`);
create index IDX9u3lu85o98y0tro95qasghg8e on `inquiry` (`deadline`);
create index IDXrcpel5hblr62lfjr9gmpk2wgi on `notice` (`deadline`);
create index IDX3ianip0mmnj1316lpeas2yw71 on `overture` (`deadline`);
create index IDXpn2uodr1wcmonf4mb9juy7loh on `round` (`kind`);
create index IDXl4saxyssjbvy3lehycgtyo9mx on `round` (`status`);

    alter table `round` 
       add constraint UK_g4u8ufh14qv6lmr5mwiulyinh unique (`ticker`);
create index IDXkn7kc8811iuqtxw6ahcnag70s on `technology` (`source`);
create index IDXnnunvyk3xx97gklsi8juy05bg on `technology` (`sector`);
create index IDXiv7jhqu4pq1w9h3v0xxggrkf7 on `tool` (`source`);
create index IDXdr92l3mhgfgkeoplifnv5x2fp on `tool` (`sector`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting` 
       add constraint `FKbu8bswc3ri7c817ei4p8ijvge` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting` 
       add constraint `FKe73agfvdtr8obvkm8rh994ep4` 
       foreign key (`round_id`) 
       references `round` (`id`);

    alter table `activity` 
       add constraint `FKcefbp3x1hxhgvlnk4n83y3o0o` 
       foreign key (`round_id`) 
       references `round` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKl4fp0cd8c008ma79n6w58xhk9` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `application` 
       add constraint `FKfy0uxra0jva9ng1ff14quuxnn` 
       foreign key (`round_id`) 
       references `round` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authorization` 
       add constraint FK_8ituf8e0qe45ej5n0tjyxxrqa 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `banner` 
       add constraint `FKmlx88rwhns1ceiyilf11mre2l` 
       foreign key (`card_id`) 
       references `card` (`id`);

    alter table `banner` 
       add constraint `FKdocr1jjfgwx9ef5jbf675l360` 
       foreign key (`patron_id`) 
       references `patron` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `entrepreneur` 
       add constraint FK_r6tqltqvrlh1cyy8rsj5pev1q 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `forum` 
       add constraint `FK49evxvl11kdqxjybm2sn41x6f` 
       foreign key (`creator_id`) 
       references `authenticated` (`id`);

    alter table `forum` 
       add constraint `FKi060kpmt16oclfryca1rf6un8` 
       foreign key (`round_id`) 
       references `round` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FKfwwpivgx5j4vw4594dgrw884q` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `message` 
       add constraint `FKik4epe9dp5q6uenarfyia7xin` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `participant` 
       add constraint `FK17hovwcvdf6h03yygtp7wlrku` 
       foreign key (`forum_id`) 
       references `forum` (`id`);

    alter table `participant` 
       add constraint `FK67h73ib586xy9hvw4vyy75fvv` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `patron` 
       add constraint `FKg7e21f3lqp5ubpiiwvcgpeiqy` 
       foreign key (`card_id`) 
       references `card` (`id`);

    alter table `patron` 
       add constraint FK_8xx5nujhuio3advxc2freyu65 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `round` 
       add constraint `FKh7pxn83gjcb886jg2lj5ipkj3` 
       foreign key (`entrepreneur_id`) 
       references `entrepreneur` (`id`);
