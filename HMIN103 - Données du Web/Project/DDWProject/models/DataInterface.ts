export interface DataInterface{
  success: boolean;
  token  : string ;
  user   : any    ;
  msg    : string ;
}



export interface Annonce {
  name: string,
  price: string,
  image: string,
  category: string,
  type: string,
  annonceImage: string,
  description: [string]
}

