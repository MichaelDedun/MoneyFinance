create table "user"
(
    id         serial not null,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    login      varchar(255),
    password   varchar(255),
    patronymic varchar(255),
    primary key (id)
);

create table user_role
(
    user_id int4 not null,
    roles   varchar(255),
    foreign key (user_id) REFERENCES "user" (id)
);

create table wallet
(
    id      serial not null,
    balance numeric(19, 2),
    name    varchar(255),
    user_id int4,
    state varchar(20),
    primary key (id),
    foreign key (user_id) REFERENCES "user"(id)
);

create table loss
(
    id          serial not null,
    money_count numeric(19, 2),
    wallet_id   int4,
    state varchar(20),
    date timestamp,
    primary key (id),
    foreign key (wallet_id) REFERENCES wallet (id)
);

create table profit
(
    id          serial not null,
    money_count numeric(19, 2),
    wallet_id   int4,
    state varchar(20),
    date timestamp,
    primary key (id),
    foreign key (wallet_id) REFERENCES wallet (id)
);

create table "transfer"
(
    id            serial not null,
    source_wallet int    not null,
    goal_wallet   int    not null,
    money_count   numeric(19, 2),
    date	  timestamp,
    FOREIGN KEY (source_wallet) REFERENCES "wallet" (id),
    FOREIGN KEY (goal_wallet) REFERENCES "wallet" (id),
    PRIMARY KEY (id)
);

create table "limit"
(
    id          serial  not null,
    wallet_id   int not null,
    money_count numeric(19, 2),
    date_from   timestamp,
    date_to     timestamp,
    FOREIGN KEY (wallet_id) REFERENCES "wallet" (id),
    PRIMARY KEY (id)
);

create table report
(
    id         serial not null,
    user_id    int4   not null,
    date_start timestamp,
    date_end   timestamp,
    primary key (id),
    foreign key (user_id) REFERENCES "user" (id)
);

create table report_profit
(
    report_id int4 NOT NULL,
    profit_id int4 NOT NULL,
    PRIMARY KEY (report_id, profit_id),
    CONSTRAINT fk_report_id
        FOREIGN KEY (report_id) REFERENCES report (id),
    CONSTRAINT fk_profit_id
        FOREIGN KEY (profit_id) REFERENCES profit (id)
);

create table report_loss
(
    report_id int4 NOT NULL,
    loss_id   int4 NOT NULL,
    PRIMARY KEY (report_id, loss_id),
    CONSTRAINT fk_report_id
        FOREIGN KEY (report_id) REFERENCES report (id),
    CONSTRAINT fk_loss_id
        FOREIGN KEY (loss_id) REFERENCES loss (id)
);

create table report_limit
(
    report_id int4 NOT NULL,
    limit_id  int4 NOT NULL,
    PRIMARY KEY (report_id, limit_id),
    CONSTRAINT fk_report_id
        FOREIGN KEY (report_id) REFERENCES report (id),
    CONSTRAINT fk_limit_id
        FOREIGN KEY (limit_id) REFERENCES "limit" (id)
);

create table payment 
( 
id serial not null, 
date timestamp, 
goal_id int4, 
parent_id int4 default null,
money_count numeric(19, 2), 
type varchar(30), 
wallet_id int4, 
primary key (id),
FOREIGN KEY (wallet_id) references "wallet"(id)
);