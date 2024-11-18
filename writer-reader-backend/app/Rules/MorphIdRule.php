<?php

namespace App\Rules;

use Closure;
use Illuminate\Contracts\Validation\DataAwareRule;
use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Translation\PotentiallyTranslatedString;

class MorphIdRule implements DataAwareRule, ValidationRule
{
    /**
     * All the data under validation.
     *
     * @var array<string, mixed>
     */
    protected array $data = [];


    /**
     * Run the validation rule.
     *
     * @param Closure(string, ?string=): PotentiallyTranslatedString $fail
     */
    public function validate(string $attribute, mixed $value, Closure $fail): void
    {
        //TODO: test if this works
        $patternType = '/able_type$/';
        $patternId = '/able_id$/';

        $matchingTypeKey = null;
        $matchingIdKey = null;
        foreach (array_keys($this->data) as $key) {
            if (preg_match($patternType, $key)) {
                $matchingTypeKey = $key;
            }
            if (preg_match($patternId, $key)) {
                $matchingIdKey = $key;
            }
        }

        if (!$matchingTypeKey || !$matchingIdKey) {
            $fail("The :attribute is invalid.");
        }

        $class = $this->data[$matchingTypeKey] ?? null;
        $id = $this->data[$matchingIdKey] ?? null;

        if (!$class || !$id) {
            $fail("The :attribute is invalid.");
        }

        if (!$class::find($id)) {
            $fail("The :attribute is invalid.");
        }
    }

    public function setData(array $data): MorphIdRule|static
    {
        $this->data = $data;

        return $this;
    }
}
