SET FOREIGN_KEY_CHECKS = 0;

-- drop table departement;
create table if not exists departement(
	dep varchar(4), 
    nomDep varchar(23), 
    chefLieu varchar(5), 
    -- reg varchar(5),   -- reg varchar(4),
	constraint pk_departement primary key (dep),
    constraint fk_departement foreign key (chefLieu) references lieu(codeInsee)
);


-- ALTER TABLE departement ADD chefLieu varchar(5);
-- INSERT INTO departement (chefLieu) select codeInsee from lieu, departement where reg = codeInsee;


-- example of lieu: insert into  lieu values ('34172','MONTPELLIER',3.876716,43.610769,'34');



insert into departement values ('01','AIN','01053');                                
insert into departement values ('02','AISNE','02408');                                  
insert into departement values ('03','ALLIER','03190');                                      
insert into departement values ('04','ALPES-DE-HAUTE-PROVENCE','04070');                                      
insert into departement values ('05','HAUTES-ALPES','05061');                                     
insert into departement values ('06','ALPES-MARITIMES','06088');                                    
insert into departement values ('07','ARDECHE','07186');                                      
insert into departement values ('08','ARDENNES','08105');                                     
insert into departement values ('09','ARIEGE','09122');                                       
insert into departement values ('10','AUBE','10387');                                       
insert into departement values ('11','AUDE','11069');                                       
insert into departement values ('12','AVEYRON','12202');                                      
insert into departement values ('13','BOUCHES-DU-RHONE','13055');                                      
insert into departement values ('14','CALVADOS','14118');                                     
insert into departement values ('15','CANTAL','15014');                                     
insert into departement values ('16','CHARENTE','16015');                                       
insert into departement values ('17','CHARENTE-MARITIME','17300');                                       
insert into departement values ('18','CHER','18033');                                     
insert into departement values ('19','CORREZE','19272');                                     
insert into departement values ('21','COTE-D''OR','21231');                                   
insert into departement values ('22','COTES-D''ARMOR','22278');  
insert into departement values ('23','CREUSE','23096');                                     
insert into departement values ('24','DORDOGNE','24322');                           
insert into departement values ('25','DOUBS','25056');                                  
insert into departement values ('26','DROME','26362');                                    
insert into departement values ('27','EURE','27229');                                      
insert into departement values ('28','EURE-ET-LOIR','28085');                                    
insert into departement values ('29','FINISTERE','29232');                                     
insert into departement values ('2A','CORSE-DU-SUD','2A004');                                      
insert into departement values ('2B','HAUTE-CORSE','2B033');                                    
insert into departement values ('30','GARD','30189');                                    
insert into departement values ('31','HAUTE-GARONNE','31555');                                    
insert into departement values ('32','GERS','32013');                                  
insert into departement values ('33','GIRONDE','33063');                                   
insert into departement values ('34','HERAULT','34172');                                     
insert into departement values ('35','ILLE-ET-VILAINE','35238');                                  
insert into departement values ('36','INDRE','36044');                                     
insert into departement values ('37','INDRE-ET-LOIRE','37261');                                  
insert into departement values ('38','ISERE','38185');                                    
insert into departement values ('39','JURA','39300');                                     
insert into departement values ('40','LANDES','40192');                                   
insert into departement values ('41','LOIR-ET-CHER','41018');                                  
insert into departement values ('42','LOIRE','42218');                                  
insert into departement values ('43','HAUTE-LOIRE','43157');                                     
insert into departement values ('44','LOIRE-ATLANTIQUE','44109');                                     
insert into departement values ('45','LOIRET','45234');                                  
insert into departement values ('46','LOT','46042');                                    
insert into departement values ('47','LOT-ET-GARONNE','47001');                               
insert into departement values ('48','LOZERE','48095');                                 
insert into departement values ('49','MAINE-ET-LOIRE','49007');                              
insert into departement values ('50','MANCHE','50502');                              
insert into departement values ('51','MARNE','51108');                                 
insert into departement values ('52','HAUTE-MARNE','52121');                                
insert into departement values ('53','MAYENNE','53130');                                       
insert into departement values ('54','MEURTHE-ET-MOSELLE','54395');                                
insert into departement values ('55','MEUSE','55029');                             
insert into departement values ('56','MORBIHAN','56260');                               
insert into departement values ('57','MOSELLE','57463');                                
insert into departement values ('58','NIEVRE','58194');                              
insert into departement values ('59','NORD','59350');                                
insert into departement values ('60','OISE','60057');                                
insert into departement values ('61','ORNE','61001');                               
insert into departement values ('62','PAS-DE-CALAIS','62041');                                 
insert into departement values ('63','PUY-DE-DOME','63113');                               
insert into departement values ('64','PYRENEES-ATLANTIQUES','64445');                                
insert into departement values ('65','HAUTES-PYRENEES','65440');                                
insert into departement values ('66','PYRENEES-ORIENTALES','66136');                                  
insert into departement values ('67','BAS-RHIN','67482');                               
insert into departement values ('68','HAUT-RHIN','68066');                               
insert into departement values ('69','RHONE','69123');                                  
insert into departement values ('70','HAUTE-SAONE','70550');                              
insert into departement values ('71','SAONE-ET-LOIRE','71270');                                
insert into departement values ('72','SARTHE','72181');                            
insert into departement values ('73','SAVOIE','73065');                              
insert into departement values ('74','HAUTE-SAVOIE','74010');                              
insert into departement values ('75','PARIS','75056');                                 
insert into departement values ('76','SEINE-MARITIME','76540');                              
insert into departement values ('77','SEINE-ET-MARNE','77288');                                
insert into departement values ('78','YVELINES','78646');                             
insert into departement values ('79','DEUX-SEVRES','79191');                              
insert into departement values ('80','SOMME','80021');                               
insert into departement values ('81','TARN','81004');                               
insert into departement values ('82','TARN-ET-GARONNE','82121');                              
insert into departement values ('83','VAR','83137');                                
insert into departement values ('84','VAUCLUSE','84007');                                
insert into departement values ('85','VENDEE','85191');                               
insert into departement values ('86','VIENNE','86194');                              
insert into departement values ('87','HAUTE-VIENNE','87085');                                
insert into departement values ('88','VOSGES','88160');                              
insert into departement values ('89','YONNE','89024');                                    
insert into departement values ('90','TERRITOIRE DE BELFORT','90010');                                   
insert into departement values ('91','ESSONNE','91228');                                     
insert into departement values ('92','HAUTS-DE-SEINE','92050');                                    
insert into departement values ('93','SEINE-SAINT-DENIS','93008');                                    
insert into departement values ('94','VAL-DE-MARNE','94028');                                     
insert into departement values ('95','VAL-D''OISE','95500');                                    
insert into departement values ('971','GUADELOUPE','97105');                                     
insert into departement values ('972','MARTINIQUE','97209');                                    
insert into departement values ('973','GUYANE','97302');                                    
insert into departement values ('974','LA REUNION','97411');                                   
insert into departement values ('976','MAYOTTE','97608');  
