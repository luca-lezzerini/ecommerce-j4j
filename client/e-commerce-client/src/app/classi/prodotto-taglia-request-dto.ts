import { LoginResponseDto } from './login-response-dto';
import { Taglia } from './taglia';
import { Prodotto } from './prodotto';

export class ProdottoTagliaRequestDto extends LoginResponseDto{
  taglia: Taglia[];
  prodotto: Prodotto;
}
