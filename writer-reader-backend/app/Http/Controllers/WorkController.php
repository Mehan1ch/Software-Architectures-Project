<?php

namespace App\Http\Controllers;

use App\Http\Requests\WorkRequest;
use App\Http\Resources\WorkResource;
use App\Models\Work;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class WorkController extends Controller
{
 public function postWork(WorkRequest $request): WorkResource
 {
     return new WorkResource(Work::create($request->all()));
 }
}
