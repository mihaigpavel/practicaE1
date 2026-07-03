create table position(
                         id_position bigint primary key,
                         name varchar(255) not null
);

create table employee(
                         id_employee bigint primary key,
                         last_name varchar(255) not null,
                         first_name varchar(255) not null ,
                         email varchar(255) not null,
                         employment_date date not null,
                         position_id bigint not  null
);

alter table employee
    add constraint fk_position
        foreign key (position_id)
            references position(id_position);
