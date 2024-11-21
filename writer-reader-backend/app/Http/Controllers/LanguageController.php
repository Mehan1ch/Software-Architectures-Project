<?php

namespace App\Http\Controllers;

use App\Http\Resources\Collections\LanguageCollection;
use App\Http\Resources\LanguageResource;
use App\Models\Language;

class LanguageController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): LanguageCollection
    {
        return new LanguageCollection(Language::paginate());
    }


    /**
     * Display the specified resource.
     */
    public function show(Language $language): LanguageResource
    {
        return new LanguageResource($language);
    }

}
