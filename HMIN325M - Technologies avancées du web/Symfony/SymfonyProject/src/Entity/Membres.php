<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Membres
 *
 * @ORM\Table(name="membres")
 * @ORM\Entity
 */
class Membres
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="smallint", nullable=false, options={"unsigned"=true})
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="mail", type="string", length=30, nullable=false)
     */
    private $mail;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=40, nullable=false)
     */
    private $password;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     */
    private $nom;
    public function getNom() { return $this->nom; }
    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=20, nullable=false)
     */
    private $prenom;
    public function getPrenom() { return $this->prenom; }

}
