

// docker üzerinden postgres çalıştırdım.
/*

 docker run -d -v
 /Users/cengizozdemir/var/folders:/data/db -p 5432:5432 -e
 POSTGRES_PASSWORD=test123 -e POSTGRES_USER=test123 -e
 POSTGRES_DB=db --name cengiz_postgres postgres

 */

CREATE TABLE genel.kullanici (
                                 id serial4 NOT NULL,
                                 adi varchar(150) NOT NULL,
                                 soyadi varchar(150) NOT NULL,
                                 kullanici_tipi int4 not null,
                                 telefon bigserial not null,
                                 eposta varchar(150) not null,
                                 parola varchar(150)	 not null,
                                 aktif bool NULL DEFAULT true,
                                 kayit_zamani timestamp NULL DEFAULT now(),
                                 CONSTRAINT kullanici_pk PRIMARY KEY (id)
);

CREATE TABLE genel.ilan (
                            id serial4 NOT NULL,
                            adi varchar(150) NOT NULL,
                            metin varchar(150) NOT NULL,
                            fiyat int4 NOT NULL,
                            goruntulenme_sayisi int4 not null,
                            aktif bool NULL ,
                            kaydeden_kullanici_id int4 null,
                            kayit_zamani timestamp NULL DEFAULT now(),
                            guncelleyen_kullanici_id int4 null,
                            guncelleme_zamani timestamp null,
                            CONSTRAINT ilan_pk PRIMARY KEY (id)
);
CREATE TABLE genel.ilan_onay (
                                 id serial4 NOT NULL,
                                 fk_ilan_id int4 not null,
                                 onay_durumu int4 NULL ,
                                 onaylayan_kullanici_id int4  null,
                                 kayit_zamani timestamp NULL DEFAULT now(),
                                 guncelleme_zamani timestamp null,
                                 CONSTRAINT ilan_onay_pk PRIMARY KEY (id)
);
CREATE TABLE genel.rapor (
                             id serial4 NOT NULL,
                             fk_ilan_id int4 not null,
                             rapor varchar(150) NOT NULL,
                             kaydeden_kullanici_id int4 null,
                             kayit_zamani timestamp NULL DEFAULT now(),
                             guncelleyen_kullanici_id int4 null,
                             guncelleme_zamani timestamp null,
                             CONSTRAINT rapor_pk PRIMARY KEY (id)
);


drop table  genel.ilan;

select * from genel.vatandas k ;
select * from genel.ilan;
select * from genel.ilan_onay io ;
select * from genel.rapor;

select * from genel.kullanici;