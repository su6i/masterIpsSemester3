<? phpnamespaceApp\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Entity\Membres;
class MembresController extends AbstractController
{
    public function listeMembres()
    {
        $membres = $this->getDoctrine()
            ->getRepository(Membres::class)
            ->findAll();
        if (!$membres)
        {
            throw $this->createNotFoundException('Aucunmembre!');
        }
        $html = ' < html > < body > Listedesmembres: < br / > < ul > ';
            foreach ($membres as $membre)
            {
                $html .= ' < li > ' . $membre->getNom() . ' < / li > ';
            }
            $html .= ' < / ul > < / body > < / html > ';
            return new Response($html);
        }
    }
?>
