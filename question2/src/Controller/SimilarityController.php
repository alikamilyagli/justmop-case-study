<?php

namespace App\Controller;

use App\Service\SimilarityService;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * Class SimilarityController
 * @package App\Controller
 * @author Ali Kamil YAĞLI
 */
class SimilarityController extends AbstractController
{
    /**
     * @Route("/api/similarity", name="similarity", methods={"POST"})
     * @param Request $request
     * @param SimilarityService $studyService
     * @return Response
     */
    public function calculate(Request $request, SimilarityService $studyService): Response
    {
        $parameters = json_decode($request->getContent(), true);
        $score = $studyService->calculate($parameters['text']);
        return $this->json(['score' => $score]);
    }
}