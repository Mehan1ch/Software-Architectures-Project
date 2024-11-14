<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

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
     * @return array<string, \Illuminate\Contracts\Validation\ValidationRule|array<mixed>|string>
     */
    public function rules(): array
    {
        return [
            "user_id" => "required|uuid|exists:users,id",
            // TODO Implement own Validation for Morphs
            "likeable_type" => "required|morph_exists:likeable_type",
            "likeable_id" => "required|morph_exists:likeable_id",
        ];
    }
}
