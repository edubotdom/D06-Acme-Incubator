
    alter table `accounting` 
       drop 
       foreign key `FKbu8bswc3ri7c817ei4p8ijvge`;

    alter table `accounting` 
       drop 
       foreign key `FKe73agfvdtr8obvkm8rh994ep4`;

    alter table `activity` 
       drop 
       foreign key `FKcefbp3x1hxhgvlnk4n83y3o0o`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKl4fp0cd8c008ma79n6w58xhk9`;

    alter table `application` 
       drop 
       foreign key `FKfy0uxra0jva9ng1ff14quuxnn`;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `authorization` 
       drop 
       foreign key FK_8ituf8e0qe45ej5n0tjyxxrqa;

    alter table `banner` 
       drop 
       foreign key `FKmlx88rwhns1ceiyilf11mre2l`;

    alter table `banner` 
       drop 
       foreign key `FKdocr1jjfgwx9ef5jbf675l360`;

    alter table `bookkeeper` 
       drop 
       foreign key FK_krvjp9eaqyapewl2igugbo9o8;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `entrepreneur` 
       drop 
       foreign key FK_r6tqltqvrlh1cyy8rsj5pev1q;

    alter table `forum` 
       drop 
       foreign key `FK49evxvl11kdqxjybm2sn41x6f`;

    alter table `forum` 
       drop 
       foreign key `FKi060kpmt16oclfryca1rf6un8`;

    alter table `investor` 
       drop 
       foreign key FK_dcek5rr514s3rww0yy57vvnpq;

    alter table `message` 
       drop 
       foreign key `FKfwwpivgx5j4vw4594dgrw884q`;

    alter table `message` 
       drop 
       foreign key `FKik4epe9dp5q6uenarfyia7xin`;

    alter table `participant` 
       drop 
       foreign key `FK17hovwcvdf6h03yygtp7wlrku`;

    alter table `participant` 
       drop 
       foreign key `FK67h73ib586xy9hvw4vyy75fvv`;

    alter table `patron` 
       drop 
       foreign key `FKg7e21f3lqp5ubpiiwvcgpeiqy`;

    alter table `patron` 
       drop 
       foreign key FK_8xx5nujhuio3advxc2freyu65;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `round` 
       drop 
       foreign key `FKh7pxn83gjcb886jg2lj5ipkj3`;

    drop table if exists `accounting`;

    drop table if exists `activity`;

    drop table if exists `administrator`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `authenticated`;

    drop table if exists `authorization`;

    drop table if exists `banner`;

    drop table if exists `bookkeeper`;

    drop table if exists `botia_bulletin`;

    drop table if exists `card`;

    drop table if exists `challenge`;

    drop table if exists `consumer`;

    drop table if exists `customization`;

    drop table if exists `entrepreneur`;

    drop table if exists `forum`;

    drop table if exists `inquiry`;

    drop table if exists `investor`;

    drop table if exists `message`;

    drop table if exists `notice`;

    drop table if exists `overture`;

    drop table if exists `participant`;

    drop table if exists `patron`;

    drop table if exists `provider`;

    drop table if exists `round`;

    drop table if exists `technology`;

    drop table if exists `tool`;

    drop table if exists `user_account`;

    drop table if exists `hibernate_sequence`;
