<?php
namespace App\Tests\Service;

use App\Service\SimilarityService;
use PHPUnit\Framework\TestCase;

/**
 * Class SimilarityServiceTest
 * @package App\Tests\Service
 * @author Ali Kamil YAĞLI
 */
class SimilarityServiceTest extends TestCase
{
    public function testSimilarity() {

        $similarityService = new SimilarityService();

        $score = $similarityService->calculate("aa");
        $this->assertEquals(3, $score);

        $score = $similarityService->calculate("ababaa");
        $this->assertEquals(11, $score);

    }
}