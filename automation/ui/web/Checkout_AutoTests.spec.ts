import { test, expect } from '@playwright/test';

/**
 * Example web UI test – AI-generated tests will extend this style.
 */
test.describe('Checkout Flow - Auto Tests', () => {
  test('should complete checkout for valid card', async ({ page }) => {
    await page.goto(process.env.WEB_BASE_URL || 'https://web.example.com');

    await page.click('text=Shop Now');
    await page.click('text=Add to Cart');
    await page.click('text=Cart');
    await page.click('text=Checkout');

    await page.fill('input[name="cardNumber"]', '4111111111111111');
    await page.fill('input[name="expiry"]', '12/30');
    await page.fill('input[name="cvv"]', '123');
    await page.click('text=Pay Now');

    await expect(page.locator('text=Payment successful')).toBeVisible();
  });
});
