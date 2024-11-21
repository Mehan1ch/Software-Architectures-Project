<?php

namespace App\Http\Requests\Store;

use App\Rules\MorphIdRule;
use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;

class StoreLikeRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, ValidationRule|array|string>
     */
    public function rules(): array
    {
        return [
            "likeable_id" => ["required", "string", new MorphIdRule],
            "likeable_type" => [Rule::in(["App\Models\Work", "App\Models\Collection","App\Models\Comment"]), "required", "string"],
        ];
    }
}
