<?php

namespace App\Http\Requests;

use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;

class UpdateWorkRequest extends FormRequest
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
            'title' => 'required|string|max:100',
            'content' => 'required|string',
            'creator_id' => 'required|uuid|exists:users,id',
            //'moderation_status' => 'required|enum',               // TODO
            'moderator_id' => 'required|uuid|exists:users,id',
            'rating_id' => 'required|uuid|exists:ratings,id',
            'language_id' => 'required|uuid|exists:languages,id',
        ];
    }
}
