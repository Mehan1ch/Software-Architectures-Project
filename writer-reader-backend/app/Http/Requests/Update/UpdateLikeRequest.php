<?php

namespace App\Http\Requests\Update;

use App\Models\Work;
use App\Rules\MorphIdRule;
use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;

class UpdateLikeRequest extends FormRequest
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
            //TODO: Check if this works
            "likeable_id" => ["required", "string", new MorphIdRule],
            "likeable_type" => [Rule::in(["App\Models\Work", "App\Models\Collection","App\Models\Comment"]), "required", "string"],
        ];
    }
}
