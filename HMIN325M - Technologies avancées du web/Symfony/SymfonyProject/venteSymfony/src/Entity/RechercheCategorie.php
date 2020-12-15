<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

class RechercheCategorie
{
    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\Categorie")
     */
    private $Categorie;

    public function getCategorie(): ?Categorie
    {
        return $this->Categorie;
    }

    public function setCategorie(?Categorie $Categorie): self
    {
        $this->Categorie = $Categorie;

        return $this;
    }
}
