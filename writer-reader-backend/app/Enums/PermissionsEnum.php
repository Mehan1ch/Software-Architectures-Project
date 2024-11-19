<?php

namespace App\Enums;

use App\Traits\EnumToArray;

enum PermissionsEnum:string
{

    use EnumToArray;
    case VIEW_CATEGORIES = 'view categories';
    case CREATE_CATEGORIES = 'create categories';
    case UPDATE_CATEGORIES = 'update categories';
    case DELETE_CATEGORIES = 'delete categories';

    case VIEW_CHAPTERS = 'view chapters';
    case CREATE_CHAPTERS = 'create chapters';
    case UPDATE_CHAPTERS = 'update chapters';
    case DELETE_CHAPTERS = 'delete chapters';

    case VIEW_CHARACTERS = 'view characters';
    case CREATE_CHARACTERS = 'create characters';
    case UPDATE_CHARACTERS = 'update characters';
    case DELETE_CHARACTERS = 'delete characters';

    case VIEW_COLLECTIONS = 'view collections';
    case CREATE_COLLECTIONS = 'create collections';
    case UPDATE_COLLECTIONS = 'update collections';
    case DELETE_COLLECTIONS = 'delete collections';

    case VIEW_COMMENTS = 'view comments';
    case CREATE_COMMENTS = 'create comments';
    case UPDATE_COMMENTS = 'update comments';
    case DELETE_COMMENTS = 'delete comments';

    case VIEW_LANGUAGES = 'view languages';
    case CREATE_LANGUAGES = 'create languages';
    case UPDATE_LANGUAGES = 'update languages';
    case DELETE_LANGUAGES = 'delete languages';

    case VIEW_LIKES = 'view likes';
    case CREATE_LIKES = 'create likes';
    case UPDATE_LIKES = 'update likes';
    case DELETE_LIKES = 'delete likes';

    case VIEW_MESSAGES = 'view messages';
    case CREATE_MESSAGES = 'create messages';
    case UPDATE_MESSAGES = 'update messages';
    case DELETE_MESSAGES = 'delete messages';

    case VIEW_RATINGS = 'view ratings';
    case CREATE_RATINGS = 'create ratings';
    case UPDATE_RATINGS = 'update ratings';
    case DELETE_RATINGS = 'delete ratings';

    case VIEW_TAGS = 'view tags';
    case CREATE_TAGS = 'create tags';
    case UPDATE_TAGS = 'update tags';
    case DELETE_TAGS = 'delete tags';

    case VIEW_USERS = 'view users';
    case CREATE_USERS = 'create users';
    case UPDATE_USERS = 'update users';
    case DELETE_USERS = 'delete users';

    case VIEW_WARNINGS = 'view warnings';
    case CREATE_WARNINGS = 'create warnings';
    case UPDATE_WARNINGS = 'update warnings';
    case DELETE_WARNINGS = 'delete warnings';


    case VIEW_WORKS = 'view works';
    case CREATE_WORKS = 'create works';
    case UPDATE_WORKS = 'update works';
    case DELETE_WORKS = 'delete works';


}
