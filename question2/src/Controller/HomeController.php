<?php

namespace App\Controller;

use App\Service\SimilarityService;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * Class HomeController
 * @package App\Controller
 * @author Ali Kamil YAĞLI
 */
class HomeController extends AbstractController
{
    /**
     * @Route("/", name="home")
     * @param SimilarityService $studyService
     * @return Response
     */
    public function home(SimilarityService $studyService): Response
    {
        return $this->render('index.html.twig');
    }

    /**
     * @Route("/detail", name="detail")
     * @return Response
     */
    public function detail(): Response
    {
        return $this->render('index.html.twig');
    }
}