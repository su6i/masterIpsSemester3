<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

class RechercheType
{
    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\Type")
     */
    private $Type;

    public function getType(): ?Type
    {
        return $this->Type;
    }

    public function setType(?Type $Type): self
    {
        $this->Type = $Type;

        return $this;
    }
}
