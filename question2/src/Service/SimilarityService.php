<?php

namespace App\Service;

/**
 * Class SimilarityService
 * @package App\Service
 * @author Ali Kamil YAĞLI
 */
class SimilarityService
{
    /**
     * Calculates similarity score of given string
     * @param string $text
     * @return int
     */
    public function calculate(string $text): int
    {
        $score = 0;
        $suffix = $text;
        while (strlen($suffix) > 0) {
            for ($i = 0; $i < strlen($suffix); $i++) {
                if ($text[$i] === $suffix[$i]) {
                    $score++;
                } else {
                    break;
                }
            }
            $suffix = substr($suffix, 1);
        }

        return $score;
    }
}
