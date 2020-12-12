<?php 
namespace App\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Entity\User;
use Symfony\Component\Annotation\Route;

class UsersController extends AbstractController {

    

    public function usersList(){
        $users = $this->getDoctrine()->getRepository(User::class)->findAll();

        foreach ($users as $user){
            $user->getId();
            $user->getEmail();
            $user->getPassword();
            $user->getName();
        }
         return $this->render('usersTemplate.html.twig', ['users'=>$users]);

    }
}

?>

