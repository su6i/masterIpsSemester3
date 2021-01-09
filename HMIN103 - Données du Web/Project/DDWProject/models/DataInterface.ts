export interface DataInterface{
  success: boolean;
  token  : string ;
  user   : any    ;
  msg    : string ;
}



export interface Product {
  name: string,
  price: string,
  image: string,
  category: string,
  type: string,
  productImage: string,
  description: [string]
}

