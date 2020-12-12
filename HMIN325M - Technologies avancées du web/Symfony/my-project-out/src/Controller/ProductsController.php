<?php 
namespace App\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Entity\Products;
use Symfony\Component\Annotation\Route;

class ProductsController extends AbstractController {

    

    public function productsList(){
        $products = $this->getDoctrine()->getRepository(Products::class)->findAll();

        foreach ($products as $product){
            $product->getId();
            $product->getName();
            $product->getPrice();
            $product->getImage();
            $product->getCategory();
            $product->getType();
            // $product->getDescription();
        }
         return $this->render('productsTemplate.html.twig', ['products'=>$products]);

    }
}








?>