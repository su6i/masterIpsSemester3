<?php

namespace App\Controller;

use App\Repository\ProduitRepository;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

class PanierController extends AbstractController
{
    /**
     * @IsGranted("ROLE_USER")
     * @Route("/panier", name="panier")
     */
    public function index(SessionInterface $session, ProduitRepository $produitRepository): Response
    {
        $panier = $session->get('panier', []);

        $panierWithData = [];

        // bouclet sur le panier pour enrichir le panierWithData
        foreach ($panier as $id => $quantity) {
            $panierWithData[] = [
                'produit' => $produitRepository->find($id),
                'quantity' => $quantity,
            ];
        }

        //ajouter une boucle pour calculer le total

        $total = 0;

        foreach ($panierWithData as $item) {
            $totalItem = $item['produit']->getPrix() * $item['quantity'];
            $total += $totalItem;
        }

        return $this->render('panier/index.html.twig', [
            'items' => $panierWithData,
            'total' => $total,
        ]);
    }

    /**
     * @IsGranted("ROLE_USER")
     * @Route("/panier/add/{id}", name="panier_add")
     */
    public function add($id, SessionInterface $session)
    {
        //si j'ai pas un panier on va l'ajouter avec []
        $panier = $session->get('panier', []);

        if (!empty($panier[$id])) {
            ++$panier[$id];
        } else {
            //dans le panier on a une cle qui est l'identifiant
            $panier[$id] = 1;
        }

        //ajouter le panier dans la session
        $session->set('panier', $panier);

        return $this->redirectToRoute('panier');

        //dd($session->get('panier'));
    }

    /**
     * @IsGranted("ROLE_USER")
     * @Route("/panier/remove/{id}", name="panier_remove")
     */
    public function remove($id, SessionInterface $session)
    {
        $panier = $session->get('panier', []);

        if (!empty($panier[$id])) {
            unset($panier[$id]);
        }
        $session->set('panier', $panier);

        return $this->redirectToRoute('panier');
    }
}
