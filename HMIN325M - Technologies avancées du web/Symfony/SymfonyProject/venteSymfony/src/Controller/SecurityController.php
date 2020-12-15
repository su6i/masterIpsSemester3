<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\InscriptionType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;


class SecurityController extends AbstractController
{
    /**
     * @Route("/inscription", name="security_inscription")
     */
    public function inscription(
        Request $request,
        EntityManagerInterface $manager,
        UserPasswordEncoderInterface $encoder
    ) {
        // creer un user pour montrer a quel objet on reli le conteni du formulaire
        $user = new User();

        /**on va instancier notre formulaire  et donner le nom de la class qui contient ce formulaire*/
        $form = $this->createForm(InscriptionType::class, $user);

        //demander au formulaire d'analyse la requete passee en parametre

        $form->handleRequest($request);

        // si le formulaire est submit et les champ sont valides il va persister ou remplir le user

        if ($form->isSubmitted() && $form->isValid()) {
            //avant d'ajouter le user il va encoder le passeword
            $hash = $encoder->encodePassword($user, $user->getPassword());

            //on modifie le mot de passe et a la place en met hash
            $user->setPassword($hash);
            $user->setRoles(['ROLE_USER']);

            $manager->persist($user);
            $manager->flush(); //ajouter dans la base

            return $this->redirectToRoute('security_login');
        }

        return $this->render('security/inscription.html.twig', [
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/connexion", name="security_login")
     */
    public function login()
    {
        return $this->render('security/login.html.twig');
    }

    /**
     * @Route("/deconnexion", name="security_logout")
     */
    public function logout()
    {
        return $this->render('security/login.html.twig');
    }

    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/security/delete/{id}",name="user_delete")
     * @Method({"DELETE"})
     */
    public function delete(Request $request, $id)
    {
        $user = $this->getDoctrine()->getRepository(User::class)->find($id);

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($user);
        $entityManager->flush();

        return $this->redirectToRoute('admin_user');
    }


}
